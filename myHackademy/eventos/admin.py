from django.contrib import admin

from .models import Evento


# Register your models here.


class EventoAdmin(admin.ModelAdmin):
    pass

admin.site.register(Evento, EventoAdmin)