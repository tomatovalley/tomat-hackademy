from django.conf.urls import url
from . import views

#from .views import LoginView 

from django.urls import path
from django.urls import re_path

from django.urls import include


from rest_framework import routers
from .views import CatchUser
from .views import EventoViewSet
from .views import GetUser
#from .views import LoginView
from .views import GetEventoDetail

app_name = 'eventos'

router = routers.DefaultRouter()
router.register('catch_user', CatchUser, 'users')
router.register('crear', EventoViewSet, 'crear')
router.register('user', GetUser, 'user')
router.register('detalles_evento', GetEventoDetail, 'events')
#router.register('Login', LoginView, 'login')

urlpatterns = [
    
    re_path('', include(router.urls)),
    path('token/', views.tokenView.as_view(), name='token'),
    path('login/', views.LoginView.as_view(), name='login')
    
]
