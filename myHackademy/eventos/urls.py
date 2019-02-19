from django.conf.urls import url
from . import views

#from .views import LoginView 

from django.urls import path
from django.urls import re_path

from django.urls import include


from rest_framework import routers
#from .views import CatchUser
#from .views import GetUser
from .views import GetEventoDetail
from .views import EventoViewSet
from .views import EmprendimientoView
from .views import CommentView
from .views import ClientView
from .views import FilterComment
app_name = 'eventos'

router = routers.DefaultRouter()
#router.register('catch_user', CatchUser, 'users')
#router.register('user', GetUser, 'user')
#router.register('Login', LoginView, 'login')


router.register('crear_evento', EventoViewSet, 'crear')
router.register('detalles_evento', GetEventoDetail, 'events')
router.register('crear_emprendimiento', EmprendimientoView, 'emprendimiento')
router.register('comment_emp', CommentView, 'comments')
router.register('clientes', ClientView, 'clients')

urlpatterns = [
    
    re_path('', include(router.urls)),
    path('comments/', FilterComment.as_view(), name='view'),
    #path('cliente_login/', Cliente.as_view(), name='hoy_cliente')
    #path('token/', views.tokenView.as_view(), name='token'),
   # path('login/', views.LoginView.as_view(), name='login')
    
]
