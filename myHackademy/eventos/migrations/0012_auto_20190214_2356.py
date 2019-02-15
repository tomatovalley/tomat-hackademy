# Generated by Django 2.1.5 on 2019-02-14 23:56

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('eventos', '0011_auto_20190214_2336'),
    ]

    operations = [
        migrations.AlterField(
            model_name='comentario',
            name='comment',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='eventos.ComentarioEmprendimiento'),
        ),
        migrations.AlterField(
            model_name='comentario',
            name='emprendimiento',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='eventos.Emprendimiento'),
        ),
    ]
