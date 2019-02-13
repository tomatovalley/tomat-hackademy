from django.contrib import admin
from django.contrib.auth.models import User
from django.contrib.auth.admin import UserAdmin as AuthUserAdmin


from .models import Evento
from .models import Client
from .models import Emprendimiento
from .models import ComentarioEmprendimiento

# Register your models here.
"""
class CommentInline(admin.StackedInline):
	model = ComentarioEmprendimiento

class CommentAdmin(admin.ModelAdmin):

	inlines = [CommentInline,]


class EmprendimientoInline(admin.StackedInline):
	model = Emprendimiento

class EmprendimientoAdmin(admin.ModelAdmin):

	inlines = [Emprendimiento,]


class EventoInline(admin.StackedInline):
	model = Evento

class EventoAdmin(admin.ModelAdmin):
    
    inlines = [Evento,]

class ClientInline(admin.StackedInline):
	model = Client


class ClientAdmin(admin.ModelAdmin):
	
	inlines = [ClientInline,]
	exclude = ('user',)


class UserAdmin(AuthUserAdmin):
	inlines = [ClientInline, EventoInline, EmprendimientoInline, CommentInline]

"""

admin.site.unregister(User)
admin.site.register(User) #AuthUserAdmin)
admin.site.register(Evento) #, EventoAdmin)
admin.site.register(Client)#, ClientAdmin)
admin.site.register(Emprendimiento) #, EmprendimientoAdmin)
admin.site.register(ComentarioEmprendimiento) #, CommentAdmin)
