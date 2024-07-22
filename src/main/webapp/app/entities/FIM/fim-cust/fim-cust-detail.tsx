import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-cust.reducer';

export const FimCustDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimCustEntity = useAppSelector(state => state.fim.fimCust.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimCustDetailsHeading">
          <Translate contentKey="fimApp.fimFimCust.detail.title">FimCust</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.id}</dd>
          <dt>
            <span id="custId">
              <Translate contentKey="fimApp.fimFimCust.custId">Cust Id</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.custId}</dd>
          <dt>
            <span id="clientId">
              <Translate contentKey="fimApp.fimFimCust.clientId">Client Id</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.clientId}</dd>
          <dt>
            <span id="idType">
              <Translate contentKey="fimApp.fimFimCust.idType">Id Type</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.idType}</dd>
          <dt>
            <span id="ctryCode">
              <Translate contentKey="fimApp.fimFimCust.ctryCode">Ctry Code</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.ctryCode}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimCust.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimCust.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.createdTs ? <TextFormat value={fimCustEntity.createdTs} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimCust.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimCust.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.updatedTs ? <TextFormat value={fimCustEntity.updatedTs} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimCust.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.recordStatus}</dd>
          <dt>
            <span id="uploadRemark">
              <Translate contentKey="fimApp.fimFimCust.uploadRemark">Upload Remark</Translate>
            </span>
          </dt>
          <dd>{fimCustEntity.uploadRemark}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-cust" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-cust/${fimCustEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimCustDetail;
