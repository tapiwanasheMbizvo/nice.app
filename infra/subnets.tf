resource "aws_subnet" "public_subnet" {

  vpc_id                                      = aws_vpc.app_vpc.id
  cidr_block                                  = var.public_subnet_cidr
  enable_resource_name_dns_a_record_on_launch = true
  map_public_ip_on_launch                     = true


  tags = {
    Name = "Public Facing Subnet"
  }

}

resource "aws_subnet" "private_subnet" {
  vpc_id     = aws_vpc.app_vpc.id
  cidr_block = var.private_subnet_cidr

  tags = {
    Name = "Private Subnet"
  }


}
