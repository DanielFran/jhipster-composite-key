import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from '../../shared';
import {
    BusinessBasicIndexService,
    BusinessBasicIndexPopupService,
    BusinessBasicIndexComponent,
    BusinessBasicIndexDetailComponent,
    BusinessBasicIndexDialogComponent,
    BusinessBasicIndexPopupComponent,
    BusinessBasicIndexDeletePopupComponent,
    BusinessBasicIndexDeleteDialogComponent,
    businessBasicIndexRoute,
    businessBasicIndexPopupRoute,
} from './';

const ENTITY_STATES = [
    ...businessBasicIndexRoute,
    ...businessBasicIndexPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BusinessBasicIndexComponent,
        BusinessBasicIndexDetailComponent,
        BusinessBasicIndexDialogComponent,
        BusinessBasicIndexDeleteDialogComponent,
        BusinessBasicIndexPopupComponent,
        BusinessBasicIndexDeletePopupComponent,
    ],
    entryComponents: [
        BusinessBasicIndexComponent,
        BusinessBasicIndexDialogComponent,
        BusinessBasicIndexPopupComponent,
        BusinessBasicIndexDeleteDialogComponent,
        BusinessBasicIndexDeletePopupComponent,
    ],
    providers: [
        BusinessBasicIndexService,
        BusinessBasicIndexPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterBusinessBasicIndexModule {}
