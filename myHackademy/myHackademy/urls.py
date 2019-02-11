"""myHackademy URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, re_path, include
from django.conf.urls import include
from rest_framework import routers
from django.conf.urls.static import static
from django.conf import settings
#from eventos.views import EventoViewSet
#from eventos.views import otherView
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)


router = routers.DefaultRouter()
#router.register('test', otherView, base_name='test')
#router.register('test', otherView, base_name='test')



urlpatterns = [

    #path('eventos/', include(router.urls)),
    path('admin/', admin.site.urls),
    path('eventos/', include('eventos.urls')),
    path('uploadimages_rest/', include('uploadimages_rest.urls')),
    path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
] + static(settings.MEDIA_URL, document_root = settings.MEDIA_ROOT )
