# ASW-PROJECT-23-24
Group project for [ASW](http://cabibbo.inf.uniroma3.it/asw/) (Software Systems Architectures) course.

Course held in the year 2023-2024 by professor [Luca Cabibbo](https://github.com/lucacabibbo) at Roma Tre University.

---

### Languages and technologies
<img src="https://skillicons.dev/icons?i=java,gradle,spring,postgres,bash,docker,kafka">

---
## 👁 Description

This project contains the code for *OrderManager*, a simple microservice application for managing product orders.

The *OrderManager* application allows you to:
* create products and change their available quantity (i.e., their inventory level);
* create orders related to one or more products;
* request validation of an order.

An order is considered valid if the order exists, if all the products ordered exist
and if the ordered quantities of each of the ordered products do not exceed the available quantities.

--- 

The *OrderManager* application consists of the following microservices:

* *product-service* manages products.
Each product has a name (which identifies it), category, quantity available, and unit price.

  Operations:
  * `POST /products` creates a new product (given name, category, quantity available and unit price, passed in the body of the request)
  * `GET /products/{name}` finds a product, given the name
  * `GET /products` finds all products
  * `POST /findproducts/bynames` finds all products that have the name included in a list of names (the list of names is passed into the body of the request)
  * `PATCH /products` updates the available quantity of a product (name data and quantity change, passed into the body of the request)


* *order-service* handles orders.
Each order has an id (which identifies it), the customer, the address, a set of order lines (each with product name and quantity), and the total.
  Operations:
  * `POST /orders` creates a new order (customer data, address, items ordered and total, passed in the body of the request)
  * `GET /orders/{id}` finds an order (given the id).
  * `GET /orders` finds all orders
  * `GET /findorders/customer/{customer}` finds all orders for a customer (given the customer)
  * `GET /findorders/product/{product}` finds all orders containing a certain product (given the product)


* *order-validation-service* allows an order to be validated.
An order validation (the outcome of a validation) consists of the order id, some order data (customer, products ordered), a validity indicator, and a reason.
  Operations:
  * `GET /ordervalidations/{id}` calculates and returns the validation of an order (given the id)


* The *api-gateway* service (exposed on port *8080*) is the application gateway API that:
  * exposes the *product-service* service on the path `/product-service` - for example, `GET /product-service/products`.
  * exposes the *order-service* service on the `/order-service` path - for example, `GET /order-service/orders/{id}`
  * exposes the *order-validation-service* on the `/order-validation-service` path - for example, `GET /order-validation-service/order-validations/{id}`

---

## 🧰 Build & Run

The shell folder contains scripts to run and test the application.

### 🛠️ Build the application
To build this application:
- Open a terminal in the folder where you cloned the repository
- Run the `gradle build` command.

### ▶️ Run the application
To run this application:
- Build the application.
- Start docker.
- Open a terminal in the folder where you cloned the repository.
- Run the `docker-compose up` command.
  
> [!TIP]  
> If you're using IntelliJ IDEA you can directly run docker-compose from the IDE.
> 
> Open the `docker-compose.yml` and press the arrows button next to `services`.

> [!IMPORTANT]
> When you run the `docker-compose up` command docker creates the images and caches them and then starts the services, or, if they are already available in the cache, takes them from the cache and starts the services. If changes are made, if you run the command directly, they will not be visible since the cached images will be used. To solve this run the `docker-compose build --no-cache` command to recreate the images (remember to build the application first).
