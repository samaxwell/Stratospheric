package com.sean.models.network;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class NetworkOutputParameters {

    private final String vpcId;
    private final String httpListenerArn;
    private final Optional<String> httpsListenerArn;
    private final String loadbalancerSecurityGroupId;
    private final String ecsClusterName;
    private final List<String> isolatedSubnets;
    private final List<String> publicSubnets;
    private final List<String> availabilityZones;
    private final String loadBalancerArn;
    private final String loadBalancerDnsName;
    private final String loadBalancerCanonicalHostedZoneId;

}
