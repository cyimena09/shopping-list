
<div class="container">
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-3">
        <a href="#" class="navbar-brand"> Ifosup-Wavre</a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a href="/todos" class="nav-link"
                ${pseudo != '' ? 'active' : '' }>Todo</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">
                <c:choose>
                    <c:when test="${pseudo == null}">
                        <a href="login" class="nav-link active">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a href="logout" class="nav-link">${pseudo} Logout</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </nav>
</div>