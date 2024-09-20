provider "aws" {
  region = var.region

}
# terraform init 

resource "aws_vpc" "app_vpc" {

  cidr_block           = var.vpc_cidr_block
  enable_dns_hostnames = true

  tags = {
    Name = "Dev ENV  VPC"
  }


}

resource "aws_internet_gateway" "app_internet_gateway" {
  vpc_id = aws_vpc.app_vpc.id

  tags = {
    Name = "Dev ENV Internet Gateway"
  }
}


resource "aws_route_table" "public_route_table" {
  vpc_id = aws_vpc.app_vpc.id

  tags = {
    Name = "Dev ENV Public Route Table"
  }

}

# Public Route
resource "aws_route" "public_route" {
  route_table_id         = aws_route_table.public_route_table.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.app_internet_gateway.id
}

resource "aws_route_table" "private_route_table" {
  vpc_id = aws_vpc.app_vpc.id

  tags = {
    Name = "Dev ENV Private Route Table"
  }

}

#Elastic IP for nat gateway 

resource "aws_eip" "eip_for_nat" {
  depends_on = [aws_internet_gateway.app_internet_gateway]
  domain     = "vpc"
  tags = {
    Name = "DEV ENV EIP for Nat Gateway"
  }
}

resource "aws_nat_gateway" "app_nat_gateway" {
  allocation_id = aws_eip.eip_for_nat.id
  subnet_id     = aws_subnet.public_subnet.id

  tags = {
    Name = "DEV ENV Public Route Table"
  }

}

# Route for Private Route Table to NAT Gateway
resource "aws_route" "private_nat" {
  route_table_id         = aws_route_table.private_route_table.id
  destination_cidr_block = "0.0.0.0/0"
  nat_gateway_id         = aws_nat_gateway.app_nat_gateway.id
}

resource "aws_route_table_association" "public_subnet_route_table_association" {
  subnet_id      = aws_subnet.public_subnet.id
  route_table_id = aws_route_table.public_route_table.id

}


resource "aws_route_table_association" "private_subnet_route_table_association" {
  subnet_id      = aws_subnet.private_subnet.id
  route_table_id = aws_route_table.private_route_table.id

}