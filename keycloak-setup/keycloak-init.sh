#!/bin/bash

cd /opt/jboss/keycloak/bin

./kcadm.sh config credentials --server http://localhost:8080/auth --realm master --user "$KEYCLOAK_USER" --password "$KEYCLOAK_PASSWORD"

./kcadm.sh create realms -s realm=ktorapis -s enabled=true

./kcadm.sh create clients -r ktorapis -s clientId="${CLIENT_USERNAME}" -s enabled=true -s clientAuthenticatorType=client-secret -s secret="${CLIENT_PASSWORD}" -s serviceAccountsEnabled=true -s 'redirectUris=["*"]'
