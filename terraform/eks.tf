module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "21.0.0"

  name               = var.cluster_name
  kubernetes_version = var.kubernetes_version

  subnet_ids = module.vpc.private_subnets

  endpoint_public_access = true

  enable_irsa = true

  enable_cluster_creator_admin_permissions = true

  eks_managed_node_groups = {
    main = {
      name = var.node_group_name

      instance_types = var.node_instance_types

      min_size     = var.node_min_size
      max_size     = var.node_max_size
      desired_size = var.node_desired_size

      subnet_ids = module.vpc.private_subnets

      capacity_type = "ON_DEMAND"
    }
  }

  tags = {
    Environment = "dev"
    Project     = "ecommerce"
  }
}