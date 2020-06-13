package mox.todo.api.controllers

import org.springframework.security.core.context.SecurityContextHolder

abstract class ControllerBase {
    protected fun userId(): String? {
        return SecurityContextHolder
            .getContext()
            .authentication
            .name
    }
}