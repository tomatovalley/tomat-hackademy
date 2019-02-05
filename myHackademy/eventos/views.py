from django.shortcuts import render
from rest_framework.exceptions import ParseError
from .forms import EventoForm

from .models import Evento
from django.contrib.auth.models import User

from .serializers import EventoSerializer

from .serializers import UserSerializer

from rest_framework.response import Response


from rest_framework.views import APIView



from rest_framework import viewsets
from rest_framework.generics import ListAPIView
from rest_framework.generics import CreateAPIView
from rest_framework import status
from django.http import Http404

from django.views.generic.edit import CreateView

from django.urls import reverse_lazy
from django.http import HttpResponseRedirect


from rest_framework.permissions import IsAuthenticated

from rest_framework.parsers import FileUploadParser

#Para autenticación por medio de token
from rest_framework import generics
from rest_framework.response import Response
from rest_framework.views import status
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login
from rest_framework_jwt.settings import api_settings
from rest_framework import permissions
from .serializers import TokenSerializer
# Create your views here.
from django.core.files.base import ContentFile


import base64
from io import BytesIO
from PIL import Image

# Get the JWT settings, add these lines after the import/from lines
jwt_payload_handler = api_settings.JWT_PAYLOAD_HANDLER
jwt_encode_handler = api_settings.JWT_ENCODE_HANDLER

class EventoViewSet(APIView):



    def post(self, request, *args, **kwargs):
        evento_json = EventoSerializer(data = request.data)#unMarshall
        if evento_json.is_valid():
            #instance = self.perform_create(evento_json)
            evento_json.save()
            
            return Response(evento_json.data, status=201)
            
        return Response(evento_json.data, status = 404)
  
class GetEvento(APIView):

       def get(self, request):

        user = Evento.objects.all()
        user_json = EventoSerializer(user, many=True)
        #place = Evento.objects.filter(name= "mundo mundo")

        return Response(user_json.data)


class DetailEvento(APIView):

    def get(self, request, pk):


        id_evento = Evento.objects.get(pk=pk)        
        id_evento_json = EventoSerializer(id_evento)

        return Response(id_evento_json.data)

    def post(self, request):
        pass



#class otherView(viewsets.ModelViewSet):
#    queryset = Evento.objects.all()
#    serializer_class = EventoSerializer


class UserViewSet(APIView):

    def get(self, request):

        user = User.objects.all()
        user_json = UserSerializer(user, many=True)
        return Response(user_json.data)

        
def evento_instance(evento):
    try:
        return user.evento
    except:
        return Evento(user=user)


"""
class CrearEvento(CreateView):

    template_name = 'evento.html'
    model = Evento
    form_class = EventoForm
    success_url =  reverse_lazy('eventos:hola')

    def form_valid(self, form):

        self.object = form.save( commit = False )
        self.object.user = self.request.user
        self.object.save()

        return HttpResponseRedirect( self.get_success_url() )



def hola(request):

    return render(request, 'hola.html', {})

"""


class LoginView(generics.CreateAPIView):
    """
    POST auth/login/
    """
    # This permission class will overide the global permission
    # class setting
    permission_classes = (permissions.AllowAny,)

    queryset = User.objects.all()

    def post(self, request, *args, **kwargs):
        username = request.data.get("username", "")
        password = request.data.get("password", "")
        user = authenticate(request, username=username, password=password)
        if user is not None:
            # login saves the user’s ID in the session,
            # using Django’s session framework.
            login(request, user)
            serializer = TokenSerializer(data={
                # using drf jwt utility functions to generate a token
                "token": jwt_encode_handler(
                    jwt_payload_handler(user)
                )})
            serializer.is_valid()
            return Response(serializer.data)
        return Response(status=status.HTTP_401_UNAUTHORIZED)

def user(self, request):

    return self.user.id