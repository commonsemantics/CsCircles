/*
 * Copyright 2013 Common Semantics (commonsemantics.org)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.commonsemantics.grails.circles.model


/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class Circle {

	String id;
	String name;
	String shortName;
	
	static mapping = {
		id generator:'uuid', sqlType: "varchar(36)"
	}
	
	static constraints = {
		id maxSize: 36
		name (nullable:false, blank: false, unique: true, maxSize:255)
		shortName  (nullable:true, blank: true, maxSize:100)
	}
	
	String getUri() {
		return "urn:circle:uuid:"+id;
	}
}
