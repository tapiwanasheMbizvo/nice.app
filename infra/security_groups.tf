
resource "aws_security_group" "public_facing_instances_sg" {

  vpc_id = aws_vpc.app_vpc.id

  tags = {
    Name = "Public Facing EC2 Instance SG"
  }

}

resource "aws_security_group" "private_instances_sg" {
  vpc_id = aws_vpc.app_vpc.id

  tags = {
    Name = "Private EC2 Instance SG"
  }
}

#this rule allows ssh access from anywhere to port 22, not recommened will update with myip or custom cidr range if its in cooperate network
resource "aws_vpc_security_group_ingress_rule" "ssh_access" {

  from_port         = 22
  to_port           = 22
  security_group_id = aws_security_group.public_facing_instances_sg.id
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "tcp"


}

#sg rule to allow inboud traffic at port 80 from anywhere in the internet 
resource "aws_vpc_security_group_ingress_rule" "allow_http_access" {
  security_group_id = aws_security_group.public_facing_instances_sg.id
  from_port         = 80
  to_port           = 80
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "tcp"

}


resource "aws_vpc_security_group_egress_rule" "allow_egress" {
  security_group_id = aws_security_group.public_facing_instances_sg.id
  ip_protocol       = "-1" #allow the instance to communicate to the internet using all protocols
  cidr_ipv4         = "0.0.0.0/0"

}

resource "aws_vpc_security_group_ingress_rule" "allow_ssh_from_public_sg" {
  security_group_id = aws_security_group.private_instances_sg.id
  # cidr_ipv4                    = "0.0.0.0/0" # change this to allow only traffic from public sg ip address
  from_port                    = 22
  to_port                      = 22
  ip_protocol                  = "tcp"
  referenced_security_group_id = aws_security_group.public_facing_instances_sg.id

  tags = {
    Name = "Allow SSH  from public sg"
  }

}


resource "aws_vpc_security_group_egress_rule" "allow_egress_from_private_instance" {
  security_group_id = aws_security_group.private_instances_sg.id
  ip_protocol       = "-1"
  cidr_ipv4         = "0.0.0.0/0"

}

