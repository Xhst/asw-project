#!/bin/bash

# trova tutti i prodotti 

echo "# trova tutti i prodotti" 
echo $(curl -s localhost:8080/product-service/products) | json_pp
echo 

