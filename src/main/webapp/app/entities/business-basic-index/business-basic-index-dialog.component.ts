import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BusinessBasicIndex } from './business-basic-index.model';
import { BusinessBasicIndexPopupService } from './business-basic-index-popup.service';
import { BusinessBasicIndexService } from './business-basic-index.service';
import { Business, BusinessService } from '../business';
import { BasicIndex, BasicIndexService } from '../basic-index';

@Component({
    selector: 'jhi-business-basic-index-dialog',
    templateUrl: './business-basic-index-dialog.component.html'
})
export class BusinessBasicIndexDialogComponent implements OnInit {

    businessBasicIndex: BusinessBasicIndex;
    isSaving: boolean;

    businesses: Business[];

    basicindices: BasicIndex[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private businessBasicIndexService: BusinessBasicIndexService,
        private businessService: BusinessService,
        private basicIndexService: BasicIndexService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.businessService.query()
            .subscribe((res: HttpResponse<Business[]>) => { this.businesses = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.basicIndexService.query()
            .subscribe((res: HttpResponse<BasicIndex[]>) => { this.basicindices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        // TODO: yelhouti: check how to do this
        this.isSaving = true;
        // if (this.businessBasicIndex.id !== undefined) {
        //     this.subscribeToSaveResponse(
        //         this.businessBasicIndexService.update(this.businessBasicIndex));
        // } else {
        //     this.subscribeToSaveResponse(
        //         this.businessBasicIndexService.create(this.businessBasicIndex));
        // }
        this.subscribeToSaveResponse(
                this.businessBasicIndexService.update(this.businessBasicIndex));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BusinessBasicIndex>>) {
        result.subscribe((res: HttpResponse<BusinessBasicIndex>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BusinessBasicIndex) {
        this.eventManager.broadcast({ name: 'businessBasicIndexListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackBusinessById(index: number, item: Business) {
        return item.id;
    }

    trackBasicIndexById(index: number, item: BasicIndex) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-business-basic-index-popup',
    template: ''
})
export class BusinessBasicIndexPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private businessBasicIndexPopupService: BusinessBasicIndexPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['businessId'] && params['basicIndexId'] && params['year'] ) {
                this.businessBasicIndexPopupService
                    .open(BusinessBasicIndexDialogComponent as Component, params['businessId'], params['basicIndexId'], params['year']);
            } else {
                this.businessBasicIndexPopupService
                    .open(BusinessBasicIndexDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
