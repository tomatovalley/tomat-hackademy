# Generated by Django 2.1.5 on 2019-02-14 23:04

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('eventos', '0006_auto_20190214_2250'),
    ]

    operations = [
        migrations.AlterField(
            model_name='comentarioemprendimiento',
            name='name',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='Emprendimiento', to='eventos.Emprendimiento'),
        ),
    ]
