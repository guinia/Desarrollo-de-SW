type Rating = {
    rate: number;
    count: number
};

type Product = {
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: Rating;
};

let allProducts: Product[] = [];
let sortBy: { [key: string]: 'asc' | 'desc' | null } = {};
const itemsPerPage = 10;

function filterProducts(value: String){
    const filteredProducts: Product[] = allProducts.filter((p: Product) => p.title.toLowerCase().includes(value.toLowerCase()) || p.description.toLowerCase().includes(value.toLowerCase()))
    drawTable(filteredProducts);
}

function paginateProducts(page: number){
    const paginatedProducts: Product[] = allProducts.slice((page - 1) * itemsPerPage, page * itemsPerPage);
    drawTable(paginatedProducts);
}

function sortProducts(prop: string) {
    if (sortBy[prop]) {
      if (sortBy[prop] === 'asc') {
        sortBy[prop] = 'desc';
      } else if (sortBy[prop] === 'desc') {
        sortBy[prop] = null;
      }
    } else {
      sortBy = { [prop]: 'asc' };
    }
  
    // @ts-ignore
    const sortedProducts: Product[] = allProducts.toSorted((a: Product, b: Product) => {
      if (sortBy[prop] === 'asc') {
        return a[prop] > b[prop] ? 1 : a[prop] < b[prop] ? -1 : 0;
      } else if (sortBy[prop] === 'desc') {
        return a[prop] < b[prop] ? 1 : a[prop] > b[prop] ? -1 : 0;
      } else {
        return allProducts;
      }
    });
  
    drawTable(sortedProducts);
}

function drawTable(product: Product[]) {

    // Prepare table HTML
    let tableHTML: string = `
    <thead>
      <tr>
        <th><button type="button" class="btn btn-link" onclick=sortProducts("id")>ID</button></th>
        <th><button type="button" class="btn btn-link" onclick=sortProducts("title")>Title</button></th>
        <th><button type="button" class="btn btn-link" onclick=sortProducts("description")>Descritpion</button></th>
        <th><button type="button" class="btn btn-link" onclick=sortProducts("price")>Price</button></th>
      </tr>
    </thead>
    <tbody>
    `;

    // Loop thru all characters to generate rows of the table
    product.forEach((p: Product) => {
        tableHTML += `<tr><td>${p.id}</td><td>${p.title}</td><td>${p.description}</td><td>${p.price}</td></tr>`;
    });

    // Close table body
    tableHTML += '</tbody>';

    // Grab table element to set its inner HTML
    document.querySelector('#tabla')!.innerHTML = tableHTML;
}

fetch('https://fakestoreapi.com/products')
    .then(res => res.json())
    .then((products: Product[]) => {

        allProducts = products;

        drawTable(products);

        const pages: number = Math.ceil(products.length / itemsPerPage);

        const paginationElement: HTMLElement = document.querySelector('#paginationElement')!;

        let pagesHTML: string = '';

        for (let index = 1; index <= pages; index++) {
            pagesHTML += `<li class="page-item"><a class="page-link" href="#" onclick="paginateProducts(${index})">${index}</a></li>`
        }

        paginationElement.innerHTML = pagesHTML;
        
        const elementoSpinner: HTMLElement = document.querySelector('#containerSpinner')!;
        elementoSpinner.style.display = 'none';

    });

