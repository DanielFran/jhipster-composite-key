import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { BusinessBasicIndex } from './business-basic-index.model';
import { BusinessBasicIndexService } from './business-basic-index.service';

@Injectable()
export class BusinessBasicIndexPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private businessBasicIndexService: BusinessBasicIndexService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.businessBasicIndexService.find(id)
                    .subscribe((businessBasicIndexResponse: HttpResponse<BusinessBasicIndex>) => {
                        const businessBasicIndex: BusinessBasicIndex = businessBasicIndexResponse.body;
                        this.ngbModalRef = this.businessBasicIndexModalRef(component, businessBasicIndex);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.businessBasicIndexModalRef(component, new BusinessBasicIndex());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    businessBasicIndexModalRef(component: Component, businessBasicIndex: BusinessBasicIndex): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.businessBasicIndex = businessBasicIndex;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
