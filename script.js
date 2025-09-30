let products = [];
let cart = [];

window.onload = function() {
    loadProducts();
    loadCart();
};

function loadProducts() {
    fetch("products.json")
    .then(response => response.json())
    .then(data => {
        products = data;
        populateCategories();
        displayProducts(products);
    });
}

function populateCategories() {
    let categories = [...new Set(products.map(p => p.category))];
    let select = document.getElementById("category-filter");
    categories.forEach(cat => {
        let option = document.createElement("option");
        option.value = cat;
        option.text = cat;
        select.appendChild(option);
    });
}

function displayProducts(productList) {
    let container = document.getElementById("products");
    container.innerHTML = "";
    productList.forEach(p => {
        let div = document.createElement("div");
        div.className = "col-md-4";
        div.innerHTML = `
            <div class="card product-card position-relative">
                <span class="product-badge">${p.category}</span>
                <img src="${p.image}" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${p.name}</h5>
                    <p class="card-text">Price: Rs.${p.price}</p>
                    <button class="btn btn-primary" onclick="addToCart(${p.id})"><i class="fas fa-cart-plus"></i> Add to Cart</button>
                </div>
            </div>
        `;
        container.appendChild(div);
    });
}

function searchProducts() {
    let query = document.getElementById("search").value.toLowerCase();
    displayProducts(products.filter(p => p.name.toLowerCase().includes(query)));
}

function filterProducts() {
    let category = document.getElementById("category-filter").value;
    displayProducts(products.filter(p => category === "" || p.category === category));
}

function sortProducts() {
    let sort = document.getElementById("sort-filter").value;
    let sorted = [...products];
    if (sort === "low") sorted.sort((a,b) => a.price - b.price);
    else if (sort === "high") sorted.sort((a,b) => b.price - a.price);
    displayProducts(sorted);
}

function addToCart(id) {
    let product = products.find(p => p.id === id);
    cart.push(product);
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
}

function loadCart() {
    let cartData = localStorage.getItem("cart");
    cart = cartData ? JSON.parse(cartData) : [];
    document.getElementById("cart-count").innerText = cart.length;
    let container = document.getElementById("cart-items");
    container.innerHTML = "";
    let total = 0;
    cart.forEach(p => {
        container.innerHTML += `<div class="cart-item">${p.name} - Rs.${p.price}</div>`;
        total += p.price;
    });
    document.getElementById("cart-total").innerText = `Total: Rs.${total}`;
}

function checkout() {
    if(cart.length === 0) { alert("Cart is empty"); return; }
    alert("Checkout successful! Total: Rs." + cart.reduce((a,b) => a+b.price,0));
    cart = [];
    localStorage.removeItem("cart");
    loadCart();
}

function showLogin() {
    document.getElementById("auth-section").style.display = "block";
}

function login() {
    alert("Login simulation — You are now logged in!");
    document.getElementById("auth-section").style.display = "none";
}

function register() {
    alert("Registration simulation — Please login!");
}

function scrollToCatalog() {
    document.getElementById("catalog-section").scrollIntoView({behavior: "smooth"});
}

function toggleCart() {
    document.getElementById("cart-sidebar").classList.toggle("active");
}
