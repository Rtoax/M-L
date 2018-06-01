#include <gtk/gtk.h>
#include <stdio.h>
#include <stdlib.h>
#include "drawArea.c"

int main(int argc, char **argv)
{
    GtkWidget *window;

    gtk_init(&argc, &argv);
    window = do_drawArea(NULL);
    g_signal_connect(window, "destroy", G_CALLBACK(gtk_main_quit), NULL);

    gtk_main();
    return 0;
}
