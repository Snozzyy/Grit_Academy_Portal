<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="tables">
    <table>
        <caption>All students</caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Town</th>
            <th>E-mail</th>
            <th>Phone number</th>
        </tr>
        <c:forEach items="${studentsData}" var="dataPoint">
            <tr>
                <td>${dataPoint[0]}</td>
                <td>${dataPoint[1]} ${dataPoint[2]}</td>
                <td>${dataPoint[3]}</td>
                <td>${dataPoint[4]}</td>
                <td>${dataPoint[5]}</td>
            </tr>
        </c:forEach>
    </table>

    <table>
    <caption>All courses</caption>
        <tr>
            <th>Name</th>
            <th>YHP</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${coursesData}" var="dataPoint">
            <tr>
                <td>${dataPoint[0]}</td>
                <td>${dataPoint[1]}</td>
                <td>${dataPoint[2]}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="forms">
    <form action="/user" method="post">
        <select name="student" id="student">
            <c:forEach items="${studentsData}" var="dataPoint">
                <option value="${dataPoint[0]}">${dataPoint[0]} - ${dataPoint[1]} ${dataPoint[2]}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Show">
    </form>
    <form action="/user" method="post">
        <select name="course" id="course">
            <c:forEach items="${coursesData}" var="dataPoint">
                <option value="${dataPoint[0]}">${dataPoint[0]}</option>
            </c:forEach>
        </select> 
        <input type="submit" value="Show">
    </form>
</div>

<div class="tables">
    <c:choose>
        <c:when test="${studentData != null}">
            <table>
                <caption>Info about student</caption>
                <tr>
                    <th>Name</th>
                    <th>YHP</th>
                    <th>Description</th>
                </tr>
                <c:forEach items="${studentData}" var="dataPoint">
                    <tr>
                        <td>${dataPoint[0]}</td>
                        <td>${dataPoint[1]}</td>
                        <td>${dataPoint[2]}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:when test="${courseData != null}">
            <table>
                <caption>Info about ${courseName}</caption>
                <tr>
                    <th>Name</th>
                    <th>Role</th>
                </tr>
                <c:forEach items="${courseData}" var="dataPoint">
                    <tr>
                        <td>${dataPoint[0]} ${dataPoint[1]}</td>
                        <td>${dataPoint[2]}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>