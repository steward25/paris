package com.airbnb.paris.processor.models

import com.airbnb.paris.annotations.Attr
import com.airbnb.paris.annotations.StyleableChild
import com.airbnb.paris.processor.android_resource_scanner.AndroidResourceId
import com.airbnb.paris.processor.framework.errors.check
import com.airbnb.paris.processor.framework.isNotPrivate
import com.airbnb.paris.processor.framework.isNotProtected
import com.airbnb.paris.processor.framework.models.SkyFieldModel
import com.airbnb.paris.processor.framework.models.SkyFieldModelFactory
import com.airbnb.paris.processor.getResourceId
import javax.lang.model.element.Element

// TODO Forward Javadoc to the generated functions/methods

internal class StyleableChildInfoExtractor
    : SkyFieldModelFactory<StyleableChildInfo>(StyleableChild::class.java) {

    override fun elementToModel(element: Element): StyleableChildInfo? {
        check(element.isNotPrivate() && element.isNotProtected(), element) {
            "Fields annotated with @StyleableChild can't be private or protected"
        }

        val attr = element.getAnnotation(StyleableChild::class.java)
        val styleableResId = getResourceId(Attr::class.java, element, attr.value)
        var defaultValueResId: AndroidResourceId? = null
        if (attr.defaultValue != -1) {
            defaultValueResId = getResourceId(Attr::class.java, element, attr.defaultValue)
        }

        return StyleableChildInfo(
                element,
                styleableResId,
                defaultValueResId)
    }
}

internal class StyleableChildInfo(
        element: Element,
        val styleableResId: AndroidResourceId,
        val defaultValueResId: AndroidResourceId?
) : SkyFieldModel(element)
