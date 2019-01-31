/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.core.yaml.config.masterslave;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.core.yaml.config.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Master-slave configuration for YAML.
 *
 * @author caohao
 */
@Getter
@Setter
public class YamlMasterSlaveConfiguration implements YamlConfiguration {
    
    private Map<String, DataSource> dataSources = new HashMap<>();
    
    private YamlMasterSlaveRuleConfiguration masterSlaveRule;
    
    private Map<String, Object> configMap = new ConcurrentHashMap<>();
    
    private Properties props = new Properties();
    
    /**
     * Unmarshal YAML master-slave configuration.
     *
     * @param yamlFile YAML file
     * @return master-slave configuration for YAML
     * @throws IOException IO Exception
     */
    public static YamlMasterSlaveConfiguration unmarshal(final File yamlFile) throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(yamlFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")
        ) {
            return new Yaml(new Constructor(YamlMasterSlaveConfiguration.class)).loadAs(inputStreamReader, YamlMasterSlaveConfiguration.class);
        }
    }
    
    /**
     * Unmarshal YAML master-slave configuration.
     *
     * @param yamlBytes YAML bytes
     * @return master-slave configuration for YAML
     * @throws IOException IO Exception
     */
    public static YamlMasterSlaveConfiguration unmarshal(final byte[] yamlBytes) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(yamlBytes)) {
            return new Yaml(new Constructor(YamlMasterSlaveConfiguration.class)).loadAs(inputStream, YamlMasterSlaveConfiguration.class);
        }
    }
}