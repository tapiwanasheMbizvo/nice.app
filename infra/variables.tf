variable "region" {
  type        = string
  description = "The aws region in which we are deploying the infra"
  default     = "af-south-1"
}
variable "vpc_cidr_block" {
  type        = string
  description = "The CIDR range for the vpc "
  default     = "10.0.0.0/16"
}

variable "public_subnet_cidr" {
  type        = string
  description = "Cidr range for the public subnet"
  default     = "10.0.16.0/20"
}

variable "private_subnet_cidr" {
  type        = string
  description = "Cidr range for the public subnet"
  default     = "10.0.32.0/20"
}

variable "public_instance_ami" {
  type        = string
  description = "The ami we want to use for the public facing ec2 instance"
  default     = "ami-0c13b2d84424f9d8d"
}

variable "private_instance_ami" {
  type        = string
  description = "The ami we want to use for the private ec2 instance, make this bigger since it will have both the web server and the database"
  default     = "ami-0c13b2d84424f9d8d"
}


variable "public_instance_type" {
  type        = string
  description = "The instance type we want for the public facing instance "
  default     = "t3.micro"
}


variable "private_instance_type" {
  type        = string
  description = "The instance type we want for the private instance "
  default     = "t3.micro"
}

variable "private_instance_key_name" {
  type        = string
  description = "The ssh key name for the private instance"
  default     = "pet-private-server"
}

variable "public_instance_key_name" {
  type        = string
  description = "The ssh key name for the pubilc instance"
  default     = "pet-public-server"
}