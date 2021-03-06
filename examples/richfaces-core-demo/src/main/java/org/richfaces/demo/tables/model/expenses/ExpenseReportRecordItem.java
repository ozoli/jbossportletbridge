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
package org.richfaces.demo.tables.model.expenses;

public class ExpenseReportRecordItem {

    private String city;
    private String day;
    private double meals;
    private double hotels;
    private double transport;

    public ExpenseReportRecordItem(String day, double meals, double hotels, double transport, String city) {
        this.city = city;
        this.day = day;
        this.meals = meals;
        this.hotels = hotels;
        this.transport = transport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getHotels() {
        return hotels;
    }

    public void setHotels(double hotels) {
        this.hotels = hotels;
    }

    public double getMeals() {
        return meals;
    }

    public void setMeals(double meals) {
        this.meals = meals;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public double getTotal() {
        return meals + hotels + transport;
    }
}
