/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.portletbridge.bridge.scope;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * BridgeRequestScope is merely the holder of the bridge's request scope state. As such its a <code>Map</code>. Its main
 * abstraction is to provide an id which a scope manager can use to identify this scope and as the gatekeeper of
 * specific attributes that are excluded from the scope.<br>
 *
 *
 * The BridgeRequestScope's main function is to prevent excluded attributes from being added to itself. The excluded
 * attributes are comprised of those defined by the specification and those set by its client (typically its manager).
 */

public interface BridgeRequestScope extends ConcurrentMap<String, Object> {

    /**
     *
     * @return unique string id representing this scope.
     */
    String getId();

    /**
     *
     * @return unique string name representing the portlet to which this scope pertains.
     */
    String getPortletName();

    /**
     *
     * @return unique string id representing the session to which this scope pertains.
     */
    String getSessionId();

    /**
     *
     * @return unique string id representing the Faces viewId to which this scope pertains.
     */
    String getViewId();

    /**
     *
     * @return unique string id representing the Portlet mode to which this scope pertains.
     */
    String getPortletMode();

    /**
     * Sets a new List of excluded attribute names. The names follow the syntax described in the specification. Any
     * currently set attribute names are lost.
     *
     * @param excludedNames
     */
    void setExcludedEntries(List<String> excludedNames);

    /**
     * Adds a new List of excluded attribute names to the existing set. Duplicates are ignored. The names follow the
     * syntax described in the specification.
     *
     * @param excludedNames
     */
    void addExcludedEntries(List<String> excludedNames);

    /**
     *
     * @param excludedNames
     *            returns the current list of excluded attribute names
     */
    List<String> getExcludedEntries(List<String> excludedNames);

    /**
     * Allows the caller to (pre-determine) if a given attribute name/value pair will be excluded or not.
     *
     * @param key
     *            name of the attribute
     * @param value
     *            of the attribute
     * @return true if the attribute will be excluded, false otherwise.
     */
    boolean isExcluded(String key, Object value);

}