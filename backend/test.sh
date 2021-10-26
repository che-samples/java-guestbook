#!/bin/bash
#
# Copyright (c) 2019-2021 Red Hat, Inc.
# This program and the accompanying materials are made
# available under the terms of the Eclipse Public License 2.0
# which is available at https://www.eclipse.org/legal/epl-2.0/
#
# SPDX-License-Identifier: EPL-2.0
#

set -e
set -u

echo "#####################################################"
echo
echo ">> I'm here"

echo "#####################################################"
echo

DIR=$(cd "$(dirname "$0")"; pwd)
echo "> this dir ${DIR}"
echo

ls -la "${DIR}"

echo "#####################################################"

export

echo "#####################################################"

echo "> whoamI ??"
whoami
echo

set +e
echo "> whereis mvn ??"
whereis mvn
set -e

mvn clean install
