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
# Create your views here.
from django.core.files.base import ContentFile


import base64
from io import BytesIO
from PIL import Image

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




def user(self, request):

    return self.user.id