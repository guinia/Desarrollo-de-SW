var allProducts = [];
var sortBy = {};
var itemsPerPage = 10;
function filterProducts(value) {
    var filteredProducts = allProducts.filter(function (p) { return p.title.toLowerCase().includes(value.toLowerCase()) || p.description.toLowerCase().includes(value.toLowerCase()); });
    drawTable(filteredProducts);
}
function paginateProducts(page) {
    var paginatedProducts = allProducts.slice((page - 1) * itemsPerPage, (page * itemsPerPage) - 1);
    drawTable(paginatedProducts);
}
function sortProducts(prop) {
    var _a;
    if (sortBy[prop]) {
        if (sortBy[prop] === 'asc') {
            sortBy[prop] = 'desc';
        }
        else if (sortBy[prop] === 'desc') {
            sortBy[prop] = null;
        }
    }
    else {
        sortBy = (_a = {}, _a[prop] = 'asc', _a);
    }
    // @ts-ignore
    var sortedProducts = allProducts.toSorted(function (a, b) {
        if (sortBy[prop] === 'asc') {
            return a[prop] > b[prop] ? 1 : a[prop] < b[prop] ? -1 : 0;
        }
        else if (sortBy[prop] === 'desc') {
            return a[prop] < b[prop] ? 1 : a[prop] > b[prop] ? -1 : 0;
        }
        else {
            return allProducts;
        }
    });
    drawTable(sortedProducts);
}
function drawTable(product) {
    // Prepare table HTML
    var tableHTML = "\n    <thead>\n      <tr>\n        <th><button type=\"button\" class=\"btn btn-link\" onclick=sortProducts(\"id\")>ID</button></th>\n        <th><button type=\"button\" class=\"btn btn-link\" onclick=sortProducts(\"title\")>Title</button></th>\n        <th><button type=\"button\" class=\"btn btn-link\" onclick=sortProducts(\"description\")>Descritpion</button></th>\n        <th><button type=\"button\" class=\"btn btn-link\" onclick=sortProducts(\"price\")>Price</button></th>\n      </tr>\n    </thead>\n    <tbody>\n    ";
    // Loop thru all characters to generate rows of the table
    product.forEach(function (p) {
        tableHTML += "<tr><td>".concat(p.id, "</td><td>").concat(p.title, "</td><td>").concat(p.description, "</td><td>").concat(p.price, "</td></tr>");
    });
    // Close table body
    tableHTML += '</tbody>';
    // Grab table element to set its inner HTML
    document.querySelector('#tabla').innerHTML = tableHTML;
}
fetch('https://fakestoreapi.com/products')
    .then(function (res) { return res.json(); })
    .then(function (products) {
    allProducts = products;
    drawTable(products);
    var pages = Math.ceil(products.length / itemsPerPage);
    var paginationElement = document.querySelector('#paginationElement');
    var pagesHTML = '';
    for (var index = 1; index <= pages; index++) {
        pagesHTML += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"paginateProducts(".concat(index, ")\">").concat(index, "</a></li>");
    }
    paginationElement.innerHTML = pagesHTML;
    var elementoSpinner = document.querySelector('#containerSpinner');
    elementoSpinner.style.display = 'none';
});
