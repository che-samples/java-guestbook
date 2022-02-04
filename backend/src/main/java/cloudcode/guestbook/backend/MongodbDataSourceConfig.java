/*---------------------------------------------------------------------------------------------
* Copyright (c) 2012-2019 Red Hat, Inc.
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*   Red Hat, Inc. - initial API and implementation
*--------------------------------------------------------------------------------------------*/
package cloudcode.guestbook.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * manages Mongo configuration data
 */
@Configuration
public class MongodbDataSourceConfig extends AbstractMongoConfiguration {

    /**
     * the name of the mongo database to use
     */
    @Override
    public final String getDatabaseName() {
        return "guestbook";
    }

    /**
     * manages connects to the mongo service address passed in as an
     * environment variable
     */
    @Override
    public final MongoClient mongoClient() {
        ServerAddress serverAddress = new ServerAddress("127.0.0.1:27017");
        MongoCredential credential = MongoCredential.createCredential("user", "guestbook", "password".toCharArray());
        return new MongoClient(serverAddress, credential, MongoClientOptions.builder().build());
    }
}
