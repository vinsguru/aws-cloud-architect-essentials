## Creating AMI

- To install java, docker and postgres client
```
sudo yum install java docker postgresql15 -y
sudo systemctl start docker.service
sudo systemctl enable docker
sudo usermod -aG docker $USER
```