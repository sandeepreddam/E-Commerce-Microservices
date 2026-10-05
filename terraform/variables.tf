variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "ap-south-2"
}

variable "cluster_name" {
  description = "EKS cluster name"
  type        = string
  default     = "ecommerce-dev-eks"
}

variable "kubernetes_version" {
  description = "Kubernetes version"
  type        = string
  default     = "1.33"
}

variable "vpc_cidr" {
  description = "VPC CIDR"
  type        = string
  default     = "10.0.0.0/16"
}

variable "node_group_name" {
  description = "EKS managed node group name"
  type        = string
  default     = "ecommerce-dev-nodes"
}

variable "node_instance_types" {
  description = "EKS worker node instance types"
  type        = list(string)
  default     = ["t3.medium"]
}

variable "node_min_size" {
  description = "Minimum number of nodes"
  type        = number
  default     = 2
}

variable "node_max_size" {
  description = "Maximum number of nodes"
  type        = number
  default     = 4
}

variable "node_desired_size" {
  description = "Desired number of nodes"
  type        = number
  default     = 2
}

variable "github_actions_role_arn" {
  description = "IAM role used by GitHub Actions"
  type        = string
  default     = "arn:aws:iam::087670584144:role/GitHubActions-ECR-EKS-Role"
}

variable "local_admin_user_arn" {
  description = "Local IAM user used for kubectl administration"
  type        = string
  default     = "arn:aws:iam::087670584144:user/sandeep-devops-cli"
}