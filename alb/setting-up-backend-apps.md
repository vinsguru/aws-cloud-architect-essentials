
We create a simple HTML with host name for demo purposes.

## order-service

```
echo "<html><body><h1>Order Service - $(hostname)</h1></body><html>" > order.html
docker run -d -p 80:80 --name nginx -v ./order.html:/usr/share/nginx/html/order.html nginx
```

## customer-service
```
echo "<html><body><h1>Customer Service - $(hostname)</h1></body><html>" > customer.html
docker run -d -p 80:80 --name nginx -v ./customer.html:/usr/share/nginx/html/customer.html nginx
```