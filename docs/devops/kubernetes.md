# Kubernetes

## Introduction

Kubernetes has become the industry standard for container orchestration. Competitors have very low market share.

Kubernetes abstracts away the need to micro manage each single machine in a cluster.

* Deploy to multiple machines

* Scalability across clusters of hosts

* Infrastructure-as-code

* Can run on an arbitrary number of servers

> Docker-compose orchestrates containers on one machine. Kubernetes is containers orchestration on multiple machines.

## Pods

* The smallest object in Kubernetes

* A selection of containers that live and die together

* Containers in a pod are scheduled to the same nodes

* The pod provides a network for the containers

* Multi-container pods can have a shared volume

Containers that should be scaled together can live in a pod as a good practice. Containers that should scale independently, they should be in different Pods. E.g. MySQL database and an Apache/PHP webserver. We might want to scale the webserver to five nodes, but not the database.
Also, we don't want to restart the database if we need to restart the webserver.

## Namespaces

All clusters in Kubernetes have the notion of namespaces.

E.g. we could have a cluster with three different namespaces for *test*, *development* and *production*.
Most people run test and development in the same cluster and production in another cluster.

Namespaces work as a virtual piece of a cluster.

## Replica set

Specifies how many replicas of a given pod specification that should be running.

Replica sets makes it unnecessary to control each pod individually.

## Node

A node is a piece of hardware that is in use. A server or a virtual machine.

## Services

Creates connectivity.

## General

Services, pods and deployments all have IP addresses.
Pods and services also have DNS names.

Pods:
`<ip>.<namespace>.pod.cluster.local`

Service:
`<name>.<namespace>.svc.cluster.local`

Replicas do not have IP addresses.
