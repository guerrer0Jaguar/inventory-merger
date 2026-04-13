
# inventory-merger


This is a microservice whose objective is to integrate two different sources (fakestoreapi and dummyjson) of inventory data into a single source. It has a scheduled process that performs the main functionality; some endpoints to query and update data are also included.



## Architecture and justification

The architecture is built as a single microservice with functionality divided into packages. This is due to the highly related nature of the features. It might be possible to split it into more components after further analysis.
## Features

- Sync process (scheduled to run every 10 min) to fetch data from external providers and integrate it into a single database.

- Endpoint to query data at:
```shell
http://localhost:8080/api/v1/inventory
```
- Endpoint to set the stock of all products to a given number:
```shell
http://localhost:8080/api/v1/inventory/restock-zeros
```


##  🚀 Installation

### Prerequisites
- JDK 8.0.x (it doesn't work with recent java versions) 
- Maven 3.9.x

1. Clone or download the project at:
```bash
  git clone https://github.com/guerrer0Jaguar/inventory-merger.git
  cd inventory-merger
```


2. Build the project:
```bash
mvn -clean install
```

3. To run the application, check the 'Usage/Examples' section.


## Usage/Examples
Once the project is built, it runs with the following command (on port 8080):
```shell
/mvnw spring-boot:run
```
An alternative command if you want to run change the default port:
```shell
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=9090"
```

FIXING PRODUCTS STOCK

There is an endpoint to set the product stock to a specified value if it is zero.
You need to provide the new value within the request body (PATCH) using the 'stock' property; see the following example:

```shell
curl -X PATCH "http://localhost:8080/api/v1/inventory/restock-zeros" -H "Content-Type:application/json" -d '{"stock" : 1000}'
```

```javaScript
{
   "status":200,
   "message":"Products updated: 20",
   "timestamp":"2026-04-12T18:30:36.693"
}
```




QUERYING PRODUCTS

Once the sync product process has finished, you can look up them executing a GET in the path /api/v1/inventory


There are multiple filters that I'll explain in the following lines (I'm using curl, but it works with any web browser).

- Minimum rating: Shows products whose rating is at least the given value. Use the 'minRating' parameter; here is an example:

```shell
curl http://localhost:8080/api/v1/inventory?minRating=4.8
```

```javaScript
[
{
"canonicId": 1,
"title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 109.95,
"description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
"category": "men's clothing",
"externalId": 1,
"provider": "ProviderA",
"rating": 120,
"stock": 0
},
{
"canonicId": 2,
"title": "Mens Casual Premium Slim Fit T-Shirts ",
"price": 22.3,
"description": "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
"category": "men's clothing",
"externalId": 2,
"provider": "ProviderA",
"rating": 259,
"stock": 0
},...
```
- Maximum price: Retrieves products whose price is at most the indicated value. Use the 'maxPrice' parameter as follows:

```shell
curl "http://localhost:8080/api/v1/inventory?maxPrice=1.29"
```
```javaScript
[
{
"canonicId": 45,
"title": "Green Bell Pepper",
"price": 1.29,
"description": "Fresh and vibrant green bell pepper, perfect for adding color and flavor to your dishes.",
"category": "groceries",
"externalId": 25,
"provider": "ProviderB",
"rating": 3.25,
"stock": 33
},
{
"canonicId": 46,
"title": "Green Chili Pepper",
"price": 0.99,
"description": "Spicy green chili pepper, ideal for adding heat to your favorite recipes.",
"category": "groceries",
"externalId": 26,
"provider": "ProviderB",
"rating": 3.66,
"stock": 3
}
]
```
- Minimal stock: retrieve the products whose stock is at least the indicated value. You have to use the word 'minStock' :

```shell
curl "http://localhost:8080/api/v1/inventory?minStock=99"
```
```javaScript
[
{
"canonicId": 21,
"title": "Essence Mascara Lash Princess",
"price": 9.99,
"description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
"category": "beauty",
"externalId": 1,
"provider": "ProviderB",
"rating": 2.56,
"stock": 99
},
{
"canonicId": 50,
"title": "Kiwi",
"price": 2.49,
"description": "Nutrient-rich kiwi, perfect for snacking or adding a tropical twist to your dishes.",
"category": "groceries",
"externalId": 30,
"provider": "ProviderB",
"rating": 4.93,
"stock": 99
}
]
```
- Provider: Filters by their original source. Accepts either 'ProviderA' or 'ProviderB' via the 'provider' query parameter.

```shell
curl "http://localhost:8080/api/v1/inventory?provider=ProviderB"
```

```javaScript
[
{
"canonicId": 1,
"title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
"price": 109.95,
"description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
"category": "men's clothing",
"externalId": 1,
"provider": "ProviderA",
"rating": 120,
"stock": 0
},
{
"canonicId": 2,
"title": "Mens Casual Premium Slim Fit T-Shirts ",
"price": 22.3,
"description": "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
"category": "men's clothing",
"externalId": 2,
"provider": "ProviderA",
"rating": 259,
"stock": 0
},....
```
- Note: It's worth mentioning that you can combine all these filters to refine your search results, for example:
```shell
curl "http://localhost:8080/api/v1/inventory?provider=ProviderA&maxPrice=10&minRating=146"
```
```javaScript
[
{
"canonicId": 7,
"title": "White Gold Plated Princess",
"price": 9.99,
"description": "Classic Created Wedding Engagement Solitaire Diamond Promise Ring for Her. Gifts to spoil your love more for Engagement, Wedding, Anniversary, Valentine's Day...",
"category": "jewelery",
"externalId": 7,
"provider": "ProviderA",
"rating": 400,
"stock": 0
},
{
"canonicId": 19,
"title": "Opna Women's Short Sleeve Moisture",
"price": 7.95,
"description": "100% Polyester, Machine wash, 100% cationic polyester interlock, Machine Wash & Pre Shrunk for a Great Fit, Lightweight, roomy and highly breathable with moisture wicking fabric which helps to keep moisture away, Soft Lightweight Fabric with comfortable V-neck collar and a slimmer fit, delivers a sleek, more feminine silhouette and Added Comfort",
"category": "women's clothing",
"externalId": 19,
"provider": "ProviderA",
"rating": 146,
"stock": 0
}
]
```
