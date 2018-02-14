import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BasicIndex } from './basic-index.model';
import { BasicIndexPopupService } from './basic-index-popup.service';
import { BasicIndexService } from './basic-index.service';

@Component({
    selector: 'jhi-basic-index-dialog',
    templateUrl: './basic-index-dialog.component.html'
})
export class BasicIndexDialogComponent implements OnInit {

    basicIndex: BasicIndex;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private basicIndexService: BasicIndexService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.basicIndex.id !== undefined) {
            this.subscribeToSaveResponse(
                this.basicIndexService.update(this.basicIndex));
        } else {
            this.subscribeToSaveResponse(
                this.basicIndexService.create(this.basicIndex));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BasicIndex>>) {
        result.subscribe((res: HttpResponse<BasicIndex>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BasicIndex) {
        this.eventManager.broadcast({ name: 'basicIndexListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-basic-index-popup',
    template: ''
})
export class BasicIndexPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private basicIndexPopupService: BasicIndexPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.basicIndexPopupService
                    .open(BasicIndexDialogComponent as Component, params['id']);
            } else {
                this.basicIndexPopupService
                    .open(BasicIndexDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
