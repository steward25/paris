package com.airbnb.paris.processor.utils

import javax.lang.model.element.*
import javax.lang.model.util.*

val Elements.VIEW_TYPE: TypeElement get() = this.getTypeElement("android.view.View")

val Elements.PROXY_TYPE: TypeElement get() = this.getTypeElement("com.airbnb.paris.proxies.Proxy")
