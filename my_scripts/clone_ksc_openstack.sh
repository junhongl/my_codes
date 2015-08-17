#!/bin/bash

CLONE_PREFIX="ssh://lijunhong@10.160.60.58:29418/zq/ksc-zq/$1"

echo "Cloning $1 ..."
cd /home/junhongl/Dev/workspace/ksc_openstack_dev
git clone $CLONE_PREFIX
cd ./$1

echo "Add gerrit remote..."
git remote add gerrit $CLONE_PREFIX

echo "Remove origin remote..."
git remote remove origin

echo "Gerrit remote update..."
git remote update

echo "checkout newmaster..."
git checkout -b newmaster gerrit/master

echo "delete master..."
git branch -d master

echo "rename newmaster..."
git branch -m newmaster master
