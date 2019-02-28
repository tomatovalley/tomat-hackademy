import jwt
import pprint
from django.http import HttpResponse


from django.shortcuts import render
from rest_framework.exceptions import ParseError
from rest_framework.renderers import TemplateHTMLRenderer
from rest_framework.parsers import JSONParser

from .serializers import EventoSerializer
from .serializers import UserSerializer
from django.shortcuts import redirect

from rest_framework.response import Response


from rest_framework.views import APIView

from rest_framework import viewsets

from rest_framework import status
from django.http import Http404

from django.views.generic.edit import CreateView

from django.http import HttpResponseRedirect

from rest_framework.permissions import IsAuthenticated

from rest_framework.parsers import FileUploadParser

#Para autenticación por medio de token
from rest_framework import generics
from rest_framework.response import Response
from rest_framework.views import status
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login
from django.contrib.auth import login as loginDjango
from rest_framework_jwt.settings import api_settings
from rest_framework import permissions


# Create your views here.
from django.core.files.base import ContentFile


from django.contrib.auth.models import User
from .models import ComentarioEmprendimiento
from .models import Evento
from .models import Emprendimiento

from .serializers import TokenSerializer
from .serializers import EmprendimientoSerializer
from .serializers import ComentarioSerializer


from rest_framework.decorators import detail_route

from rest_framework.decorators import list_route
# Get the JWT settings, add these lines after the import/from lines
jwt_payload_handler = api_settings.JWT_PAYLOAD_HANDLER
jwt_encode_handler = api_settings.JWT_ENCODE_HANDLER



class EventoViewSet(viewsets.ModelViewSet):
    
    permission_classes = (permissions.IsAuthenticated,)
    
    queryset = Evento.objects.all()
    serializer_class = EventoSerializer
    http_method_names =['post']



class GetEventoDetail(viewsets.ModelViewSet):

    #permissions_classes = (permissions.AllowAny,)
    permission_classes = (permissions.IsAuthenticated,)


    queryset = Evento.objects.all()
    serializer_class = EventoSerializer
    http_method_names = ['get']

    @detail_route()
    def evento_detail(self, request, pk):

        evento = self.get_object()
        evento_ = Evento.objects.filter(name = evento)
        evento_json = EventoSerializer(evento_, many=True)
        return Response(evento_json.data)


class EmprendimientoView(viewsets.ModelViewSet):

    #parser_classes = (FileUploadParser,)
    permissions_classes = (permissions.AllowAny, )

    queryset  = Emprendimiento.objects.all()
    serializer_class = EmprendimientoSerializer



class Prueba(APIView):

    def get(self, request, *args, **kwargs):

        prueba = Emprendimiento.objects.all()
        prueba_json = EmprendimientoSerializer(prueba, many= True)

        return Response(prueba_json.data, status=200)


class CommentView(viewsets.ModelViewSet):


    #permissions_classes = (permissions.IsAuthenticated, )

    queryset = ComentarioEmprendimiento.objects.all()
    serializer_class = ComentarioSerializer



class ClientView(viewsets.ModelViewSet):

    permissions_classes = (permissions.AllowAny,)
    queryset = User.objects.all()
    serializer_class = UserSerializer

    #def perform_create(self, serializers):

    #    instance = serializers.save()
    #    instance.set_password(instance.password)
    #    instance.save()


class FilterComment(APIView):

    def get(self, request):

        user = ComentarioEmprendimiento.objects.all()
        user_json = ComentarioSerializer(user, many=True)

        return Response(user_json.data, status=200)


class tokenView(APIView):

    permissions_classes = (permissions.AllowAny,)
    serializer_class = TokenSerializer

    def post(self, request, *args, **kwargs):
        context = {
            'response':'response'
        }
        return HttpResponse(context)



"""
class UserViewSet(APIView):

    permission_classes = (IsAuthenticated,)

    def get(self, request):

        user = User.objects.all()
        user_json = UserSerializer(user, many=True)
        return Response(user_json.data)
"""
       


class LoginView(generics.CreateAPIView):
   
    # This permission class will overide the global permission
    # class setting
    permission_classes = (permissions.AllowAny,)

    queryset = User.objects.all()
    serializer_class = UserSerializer

    def post(self, request, *args, **kwargs):
        username = request.data.get("username", "")
        password = request.data.get("password", "")
        email = request.data.get("email", "")

        user = authenticate(request, username=username, password = password)
        #user2 = authenticate(request, email=email, password=password)

        if user  is not None:
            # login saves the user’s ID in the session,
            # using Django’s session framework.
            loginDjango(request, user)

            serializer = TokenSerializer(data={
                # using drf jwt utility functions to generate a token
                "token": jwt_encode_handler(
                    jwt_payload_handler(user)
                )})
            serializer.is_valid()
            return Response(serializer.data)
        return Response(status=status.HTTP_401_UNAUTHORIZED)





"""
def evento_instance(evento):
    try:
        return user.evento
    except:
        return Evento(user=user)

"""

def user(self, request, user):

    try:
        return user.username
    except:
        return User(user=user)



