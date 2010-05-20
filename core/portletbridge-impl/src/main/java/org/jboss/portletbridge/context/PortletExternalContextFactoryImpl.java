package org.jboss.portletbridge.context;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.ExternalContextFactory;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.faces.Bridge;

import org.jboss.portletbridge.util.BridgeLogger;

public class PortletExternalContextFactoryImpl
        extends ExternalContextFactory {

    private ExternalContextFactory mWrapped;
    private static final Logger _log = BridgeLogger.FACES.getLogger();

    public PortletExternalContextFactoryImpl(ExternalContextFactory factory) {
        mWrapped = factory;
        if (_log.isLoggable(Level.FINE)) {
            _log.fine("Portal - specific PortletExternalContextFactory has initialised");
        }
    }

    public ExternalContext getExternalContext(Object context, Object request,
            Object response)
            throws FacesException {

        if ((null == context) || (null == request) || (null == response)) {
            throw new NullPointerException(
                    "One or more parameters for a external context instantiation is null");
        }
        ExternalContext externalContext;
        Object portletPhase = request instanceof PortletRequest ? ((PortletRequest) request).getAttribute(Bridge.PORTLET_LIFECYCLE_PHASE) : null;
        if (null != portletPhase) {
            if (Bridge.PortletPhase.ACTION_PHASE.equals(portletPhase)
                    && (context instanceof PortletContext)
                    && (request instanceof ActionRequest)
                    && (response instanceof ActionResponse)) {
                externalContext = new ActionRequestExternalContextImpl(
                        (PortletContext) context, (ActionRequest) request,
                        (ActionResponse) response);
                if (_log.isLoggable(Level.FINE)) {
                    _log.fine("Portal request - create portal version of the ExternalContext for action request");
                }
            } else if (Bridge.PortletPhase.RENDER_PHASE.equals(portletPhase)
                    && (context instanceof PortletContext)
                    && (request instanceof RenderRequest)
                    && (response instanceof RenderResponse)) {
                externalContext = new RenderPortletExternalContextImpl(
                        (PortletContext) context, (RenderRequest) request,
                        (RenderResponse) response);
                if (_log.isLoggable(Level.FINE)) {
                    _log.fine("Portal request - create portal version of the ExternalContext for render response");
                }
            } else if (Bridge.PortletPhase.RESOURCE_PHASE.equals(portletPhase)
                    && (context instanceof PortletContext)
                    && (request instanceof ResourceRequest)
                    && (response instanceof ResourceResponse)) {
                externalContext = new ResourceRequestExternalContextImpl(
                        (PortletContext) context, (ResourceRequest) request,
                        (ResourceResponse) response);
                if (_log.isLoggable(Level.FINE)) {
                    _log.fine("Portal request - create portal version of the ExternalContext for resource response");
                }
            } else if (Bridge.PortletPhase.EVENT_PHASE.equals(portletPhase)
                    && (context instanceof PortletContext)
                    && (request instanceof EventRequest)
                    && (response instanceof EventResponse)) {
                externalContext = new EventRequestExternalContextImpl(
                        (PortletContext) context, (EventRequest) request,
                        (EventResponse) response);
                if (_log.isLoggable(Level.FINE)) {
                    _log.fine("Portal request - create portal version of the ExternalContext for event request");
                }
            } else {
                throw new FacesException("Invalid types for portlet phase "
                        + portletPhase.toString());
            }
        } else {
            externalContext = getWrapped().getExternalContext(context, request, response);
        }
        return externalContext;
    }

    @Override
    public ExternalContextFactory getWrapped() {
        return mWrapped;
    }

}