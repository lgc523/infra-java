package dev.spider.io.listen;

import javax.jmdns.*;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @author spider
 */
public class JmDnsDiscover {


    private static class SampleListener implements ServiceListener, ServiceTypeListener {
        @Override
        public void serviceAdded(ServiceEvent event) {
            ServiceInfo info = event.getDNS().getServiceInfo(event.getType(), event.getName());
            System.out.println("Service added: " + event.getDNS().getName());
        }

        @Override
        public void serviceRemoved(ServiceEvent event) {
            System.out.println("Service removed: " + event.getInfo());
        }

        @Override
        public void serviceResolved(ServiceEvent event) {
            ServiceInfo info = event.getDNS().getServiceInfo(event.getType(), event.getName());
            System.out.println(info.getName());
            System.out.println("Service resolved: " + event.getName());
        }

        @Override
        public void serviceTypeAdded(ServiceEvent event) {
            System.out.println("serviceTypeAdded: " + event.getDNS().getHostName());
        }

        @Override
        public void subTypeForServiceTypeAdded(ServiceEvent event) {
            System.out.println("subTypeForServiceTypeAdded: " + event.getName());

        }
    }

    static final String serviceType = "_http._icmp.local.";

    public static void main(String[] args) throws InterruptedException {

        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Add a service listener
            SampleListener sampleListener = new SampleListener();
            jmdns.addServiceTypeListener(sampleListener);
            jmdns.addServiceListener(serviceType, sampleListener);
            ServiceInfo[] list = jmdns.list(serviceType);

            System.out.println("start...");

            // Wait a bit
            ServiceInfo info = ServiceInfo.create("_http._tcp.local.", "tcp", 1234, "fuck");
            ServiceInfo info1 = ServiceInfo.create("_http.icmp.local.", "icmp", 1235, "ic");
            jmdns.registerService(info);
            jmdns.registerService(info1);
            jmdns.unregisterService(info);
            jmdns.unregisterService(info1);
            jmdns.unregisterAllServices();
            jmdns.removeServiceTypeListener(sampleListener);
            jmdns.removeServiceTypeListener(sampleListener);
//            for (ServiceInfo serviceInfo : list) {
//                System.out.println(serviceInfo.getName());
//            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
