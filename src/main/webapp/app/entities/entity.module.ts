import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterBusinessModule } from './business/business.module';
import { JhipsterBasicIndexModule } from './basic-index/basic-index.module';
import { JhipsterBusinessBasicIndexModule } from './business-basic-index/business-basic-index.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterBusinessModule,
        JhipsterBasicIndexModule,
        JhipsterBusinessBasicIndexModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEntityModule {}
