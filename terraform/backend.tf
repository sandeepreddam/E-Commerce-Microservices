terraform {
  backend "s3" {
    bucket       = "ecommerce-terraform-state-087670584144"
    key          = "ecommerce-dev/terraform.tfstate"
    region       = "ap-south-2"
    use_lockfile = true
  }
}