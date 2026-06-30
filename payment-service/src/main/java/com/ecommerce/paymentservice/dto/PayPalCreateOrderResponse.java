package com.ecommerce.paymentservice.dto;

import java.util.List;

public class PayPalCreateOrderResponse {

    private String id;
    private String status;
    private List<PayPalLink> links;

    public PayPalCreateOrderResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PayPalLink> getLinks() {
        return links;
    }

    public void setLinks(List<PayPalLink> links) {
        this.links = links;
    }

    public static class PayPalLink {
        private String href;
        private String rel;
        private String method;

        public PayPalLink() {
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }
}