import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from '../../shared';
import {
    BasicIndexService,
    BasicIndexPopupService,
    BasicIndexComponent,
    BasicIndexDetailComponent,
    BasicIndexDialogComponent,
    BasicIndexPopupComponent,
    BasicIndexDeletePopupComponent,
    BasicIndexDeleteDialogComponent,
    basicIndexRoute,
    basicIndexPopupRoute,
} from './';

const ENTITY_STATES = [
    ...basicIndexRoute,
    ...basicIndexPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BasicIndexComponent,
        BasicIndexDetailComponent,
        BasicIndexDialogComponent,
        BasicIndexDeleteDialogComponent,
        BasicIndexPopupComponent,
        BasicIndexDeletePopupComponent,
    ],
    entryComponents: [
        BasicIndexComponent,
        BasicIndexDialogComponent,
        BasicIndexPopupComponent,
        BasicIndexDeleteDialogComponent,
        BasicIndexDeletePopupComponent,
    ],
    providers: [
        BasicIndexService,
        BasicIndexPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterBasicIndexModule {}
