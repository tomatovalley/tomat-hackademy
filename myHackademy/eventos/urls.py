from django.conf.urls import url
from . import views

from .views import LoginView 

from django.urls import path
from django.urls import re_path

from django.urls import include


from rest_framework import routers


app_name = 'eventos'


urlpatterns = [
    

    path('crear', views.EventoViewSet.as_view(), name='api-view'),
    path('get_eventos', views.GetEvento.as_view(), name='api2'),
    re_path(r'^get_eventos/(?P<pk>[0-9]+)$', views.DetailEvento.as_view(), name='api-3'),
    path('auth/login/', LoginView.as_view(), name="auth-login")
    #re_path('get_imagen', views.imageString.as_view(), name='api-3')

    #path('user', views.UserViewSet.as_view(), name='api-view-user_'),
    #path('crear_evento', views.CrearEvento.as_view(),  name='crear_evento'),
    #path('hola', views.hola, name='hola'),
]
