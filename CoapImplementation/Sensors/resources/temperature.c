#include "contiki.h"
#include "coap-engine.h"
#include "coap-observe.h"
#include <stdio.h>
/* Including the following file to pass the temperature value to the node */
#include "appVar.h"

static void res_get_handler(coap_message_t *request, coap_message_t *response, uint8_t *buffer, uint16_t preferred_size, int32_t *offset);
static void res_event_handler(void);

EVENT_RESOURCE(res_temperature,
                "title=\"Temperature sensor\";obs;rt=\"Temperature\"",
                res_get_handler,
                NULL,
                NULL,
                NULL,
                res_event_handler
);

static void res_event_handler(void){
    coap_notify_observers(&res_temperature);
}

static void res_get_handler(coap_message_t *request, coap_message_t *response, uint8_t *buffer, uint16_t preferred_size, int32_t *offset){

}