async function getProducts() {
  const response = await fetch(
    "http://localhost:8080/api/products/allProducts"
  );
  const products = await response.json();

  const productsContainer = document.getElementById("products");

  products.forEach((product) => {
    productsContainer.innerHTML += `
           <div class="product-card">
                <h3>${product.name}</h3>
                <p>${product.description}</p>
                <p>Price: $${product.price}</p>
                <p>Quantity: ${product.quantity}</p>
            </div> 
        `;
  });
}

window.onload = () => {
  getProducts();
};
