heat stack-create -f boot-from-volume.yaml -P "image=cirros-img;flavor=m1.tiny;private_net_name=test_net;private_net_gateway=192.168.12.1;private_net_cidr=192.168.12.0/24;private_net_pool_start=192.168.12.2;private_net_pool_end=192.168.12.254" my_stack
