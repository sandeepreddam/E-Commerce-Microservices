module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "21.0.0"

  name               = var.cluster_name
  kubernetes_version = var.kubernetes_version

  vpc_id     = module.vpc.vpc_id
  subnet_ids = module.vpc.private_subnets

  endpoint_public_access  = true
  endpoint_private_access = true

  authentication_mode = "API_AND_CONFIG_MAP"

  enable_cluster_creator_admin_permissions = false

  addons = {
    coredns = {
      most_recent = true
    }

    kube-proxy = {
      most_recent = true
    }

    vpc-cni = {
      most_recent = true
    }

    eks-pod-identity-agent = {
      most_recent = true
    }
  }

  access_entries = {

    github_actions = {
      principal_arn = var.github_actions_role_arn
      type          = "STANDARD"

      policy_associations = {
        admin = {
          policy_arn = "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy"

          access_scope = {
            type = "cluster"
          }
        }
      }
    }

    local_admin = {
      principal_arn = var.local_admin_user_arn
      type          = "STANDARD"

      policy_associations = {
        admin = {
          policy_arn = "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy"

          access_scope = {
            type = "cluster"
          }
        }
      }
    }
  }

  eks_managed_node_groups = {

    main = {
      name = var.node_group_name

      ami_type = "AL2023_x86_64_STANDARD"

      instance_types = var.node_instance_types

      min_size     = var.node_min_size
      max_size     = var.node_max_size
      desired_size = var.node_desired_size

      subnet_ids = module.vpc.private_subnets

      capacity_type = "ON_DEMAND"

      disk_size = 20

      tags = {
        Name        = var.node_group_name
        Project     = "ecommerce"
        Environment = "dev"
      }
    }
  }

  tags = {
    Project     = "ecommerce"
    Environment = "dev"
    Terraform   = "true"
  }
}